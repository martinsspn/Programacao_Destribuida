import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserInterfaceComponent } from "./user-interface/user-interface.component";

const routes: Routes =[
    {
        path: '', component: UserInterfaceComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserInterfaceRoutingModule {}