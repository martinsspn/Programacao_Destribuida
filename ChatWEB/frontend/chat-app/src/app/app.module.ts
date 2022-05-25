import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login/login.component';
import { MessageService } from './message.service';
import { UserInterfaceComponent } from './user-interface/user-interface/user-interface.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserInterfaceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [MessageService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
