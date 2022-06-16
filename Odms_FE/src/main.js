import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

// global style
import './assets/css/index.less';

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
