import {
  BadRequestException,
  Body,
  Controller,
  Get,
  Logger,
  Post,
  Res,
} from '@nestjs/common';
import { Provider } from 'oidc-provider';
import { Oidc, InteractionHelper } from 'nest-oidc-provider';
import { Response } from 'express';
import { HttpService } from '@nestjs/axios';
import { Observable } from 'rxjs';
import { AxiosResponse } from 'axios';
import { AUTH_RESOURCE_SERVER_URL } from '../environment';

@Controller('/interaction')
export class InteractionController {
  private readonly logger = new Logger(InteractionController.name);

  constructor(
    private readonly provider: Provider,
    private httpService: HttpService
  ) {}

  @Get(':uid')
  async login(
    @Oidc.Interaction() interaction: InteractionHelper,
    @Res() res: Response
  ) {
    const { prompt, params, uid } = await interaction.details();

    const client = await this.provider.Client.find(params.client_id as string);

    res.render(prompt.name, {
      details: prompt.details,
      client,
      params,
      uid,
    });
  }

  @Post(':uid')
  async loginCheck(
    @Oidc.Interaction() interaction: InteractionHelper,
    @Body() form: Record<string, string>
  ) {
    const { prompt, params, uid } = await interaction.details();

    if (!form.user || !form.password) {
      throw new BadRequestException('missing credentials');
    }

    if (prompt.name !== 'login') {
      throw new BadRequestException('invalid prompt name');
    }

    this.logger.debug(`Login : ${uid} with user ${form.user}`);
    this.logger.debug(`Client ID: ${params.client_id}`);

    await interaction.finished(
      {
        login: {
          accountId: form.user,
          infos: this.loginAndGetInfos(form),
        },
      },
      { mergeWithLastSubmission: false }
    );
  }

  @Post(':uid/confirm')
  async confirmLogin(@Oidc.Interaction() interaction: InteractionHelper) {
    const interactionDetails = await interaction.details();
    const { prompt, params, session } = interactionDetails;
    let { grantId } = interactionDetails;

    const grant = grantId
      ? await this.provider.Grant.find(grantId)
      : new this.provider.Grant({
          accountId: session.accountId,
          clientId: params.client_id as string,
        });

    if (prompt.details.missingOIDCScope) {
      const scopes = prompt.details.missingOIDCScope as string[];
      grant.addOIDCScope(scopes.join(' '));
    }

    if (prompt.details.missingOIDCClaims) {
      grant.addOIDCClaims(prompt.details.missingOIDCClaims as string[]);
    }

    if (prompt.details.missingResourceScopes) {
      for (const [indicator, scopes] of Object.entries(
        prompt.details.missingResourceScopes
      )) {
        grant.addResourceScope(indicator, scopes.join(' '));
      }
    }

    grantId = await grant.save();

    await interaction.finished(
      { consent: { grantId } },
      { mergeWithLastSubmission: true }
    );
  }

  @Get(':uid/abort')
  async abortLogin(@Oidc.Interaction() interaction: InteractionHelper) {
    const result = {
      error: 'access_denied',
      error_description: 'End-user aborted interaction',
    };

    await interaction.finished(result, { mergeWithLastSubmission: false });
  }

  protected loginAndGetInfos(
    form: Record<string, string>
  ): Observable<AxiosResponse<object>> {
    try {
      return this.httpService.post(`${AUTH_RESOURCE_SERVER_URL}/login`, form);
    } catch (error) {
      throw new BadRequestException(error, 'Error during auth login');
    }
  }
}
