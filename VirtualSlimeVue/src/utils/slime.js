import axios from 'axios'
import Qs from 'qs'

const slime = axios.create({
    timeout: 5000,
    transformRequest: [function (data) {
        return Qs.stringify(data)
    }],
})

slime.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
slime.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)
export default slime

