import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [{
  path:'', 
  loadChildren: () =>import('./login/login.module').then(m => m.LoginModule)
},
{
  path:'chat',
  loadChildren: () => import('./user-interface/user-interface.module').then(m=> m.UserInterfaceModule)
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
