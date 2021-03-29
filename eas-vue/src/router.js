import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/Views/Home.vue';
import Articles from '@/Views/Articles.vue';
import AboutUs from '@/Views/AboutUs.vue';
import Issues from '@/Views/Issues.vue';
import Dungeon from '@/Views/Dungeon.vue';
import Users from '@/Views/Users.vue';
import IssuesCatalog from '@/Views/IssuesCatalog.vue';
import ArticlesCatalog from '@/Views/ArticlesCatalog.vue';

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/articles/:id',
            name: 'articles',
            component: Articles
        },
        {
            path: '/articles/:searchString',
            name: 'articlesBySearch',
            component: Articles
        },
        {
            path: '/aboutus',
            component: AboutUs
        },
        {
            path: '/issues',
            component: Issues
        },
        {
            path: '/dungeon',
            component: Dungeon
        },
        {
            path: '/dungeon/users',
            component: Users
        },
        {
            path: '/dungeon/issues-catalog',
            component: IssuesCatalog
        },
        {
            path: '/dungeon/issues-catalog/details/:id',
            name: 'articleCatalog',
            component: ArticlesCatalog
        }

        
    ]
});