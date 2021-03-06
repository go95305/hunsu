import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';
import 'vuetify/dist/vuetify.min.css';
import store from './store/store';
import axios from 'axios';
import VueAxios from 'vue-axios';
import VueCookies from 'vue-cookies';
import 'chart.js';
import 'hchs-vue-charts';
// 아이콘 리스트

Vue.use(window.VueCharts);

Vue.use(VueAxios, axios);
Vue.use(VueCookies);
Vue.config.productionTip = false;

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
