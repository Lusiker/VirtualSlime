import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import 'vant/es/button/style'

import { Button, Search, Image as VanImage, Tab, Tabs, NavBar, Field, Col, Row, Tabbar, TabbarItem, Swipe, SwipeItem, Grid, GridItem, Card, Cell, CellGroup, Tag, Popup } from 'vant'

createApp(App).use(store).use(router)
    .use(Button)
    .use(Search)
    .use(VanImage)
    .use(Tab)
    .use(Tabs)
    .use(NavBar)
    .use(Field)
    .use(Cell)
    .use(CellGroup)
    .use(Col)
    .use(Row)
    .use(Tabbar)
    .use(TabbarItem)
    .use(Swipe)
    .use(SwipeItem)
    .use(Grid)
    .use(GridItem)
    .use(Card)
    .use(Tag)
    .use(Popup)
    .mount('#app')
