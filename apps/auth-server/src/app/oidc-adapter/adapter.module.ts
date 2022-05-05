import { Module } from '@nestjs/common';
import { AdapterService } from './oidc.adapter.service';

@Module({
    providers: [AdapterService],
    exports: [AdapterService]
})
export class OidcAdapterModule {}
