import Vue from 'vue'
import Router from 'vue-router'
import MchList from '@/pages/mchList'
import MealList from '@/pages/mealList'

Vue.use(Router)

const getAbsolutePath = () => {
  const path = location.pathname
  return path.substring(0, path.lastIndexOf('/') + 1)
}

export default new Router({
  mode: 'history',
  base: getAbsolutePath(),
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
