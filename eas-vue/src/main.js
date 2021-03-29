import Vue from 'vue'
import App from './App.vue'
import store from './store'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { DropdownPlugin, TablePlugin } from 'bootstrap-vue'
import { CarouselPlugin } from 'bootstrap-vue'
import router from './router'



Vue.config.productionTip = false

Vue.use(CarouselPlugin)
Vue.use(DropdownPlugin)
Vue.use(TablePlugin)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
