import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { LoginRoutingModule } from './login-routing.module'
import { MatSnackBarModule } from '@angular/material/snack-bar'

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        LoginRoutingModule,
        MatSnackBarModule
    ]
})
export class LoginModule {}