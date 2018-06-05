import Vue from 'vue'
import Router from 'vue-router'
import MchList from '@/pages/mchList'
import MealList from '@/pages/mealList'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: MchList
    },
    {
      path: '/meals',
      name: 'meals',
      component: MealList
    }
  ]
})
