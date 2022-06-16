import { createApp } from 'vue'
import App from './App.vue'
// router
import router from './router'
//pinia
import { createPinia } from 'pinia'

// navie UI
import naive from 'naive-ui'

// global style
import './assets/css/index.less';

//tailwind
import '@/assets/index.css'

const app = createApp(App)
app.use(router)
app.use(createPinia())
app.use(naive)
app.mount('#app')
