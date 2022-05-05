import { Controller, Get } from '@nestjs/common';

@Controller()
export class AppController {
  constructor() {}

  @Get("/home")
  getData() {
    return { message: 'Welcome to auth-server!' };
  }
}
