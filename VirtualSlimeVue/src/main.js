import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import 'vant/es/button/style'

import {
    Button,
    Search,
    Image as VanImage,
    Tab,
    Tabs,
    NavBar,
    Field,
    Col,
    Row,
    Tabbar,
    TabbarItem,
    Swipe,
    SwipeItem,
    Grid,
    GridItem,
    Card,
    Cell,
    CellGroup,
    Tag,
    Popup,
    Picker,
    Calendar,
    Uploader,
    List,
    Icon,
    Checkbox,
    RadioGroup,
    Radio,
    ActionBar,
    ActionBarIcon,
    ActionBarButton,
    ShareSheet,
    Rate,
    SwipeCell,
    SubmitBar
} from 'vant'

createApp(App).use(store).use(router)
    .use(Button)
    .use(Search)
    .use(VanImage)
    .use(Tab)
    .use(Tabs)
    .use(NavBar)
    .use(Field)
    .use(Col)
    .use(Row)
    .use(Tabbar)
    .use(TabbarItem)
    .use(Swipe)
    .use(SwipeItem)
    .use(Grid)
    .use(GridItem)
    .use(Card)
    .use(Cell)
    .use(CellGroup)
    .use(Tag)
    .use(Popup)
    .use(Picker)
    .use(Calendar)
    .use(Uploader)
    .use(List)
    .use(Icon)
    .use(Checkbox)
    .use(RadioGroup)
    .use(Radio)
    .use(ActionBar)
    .use(ActionBarIcon)
    .use(ActionBarButton)
    .use(ShareSheet)
    .use(Rate)
    .use(SwipeCell)
    .use(SubmitBar )
    .mount('#app')
