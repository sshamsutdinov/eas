import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IssueComponent} from "./issue/issue.component";
import {MainContentComponent} from "./main-content/main-content.component";
import {ArticleComponent} from "./article/article.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {SignInComponent} from "./sign-in/sign-in.component";

const routes: Routes = [
  {path: '', redirectTo: '/issue', pathMatch: 'full'},
  {path: 'issue', component: IssueComponent},
  {path: 'content/:id', component: MainContentComponent},
  {path: 'issue/:number', component: ArticleComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'signin', component: SignInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
