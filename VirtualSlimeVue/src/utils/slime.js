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

slime.interceptors.response.use(
    response => {
        let res = response.data;
        if (response.config.responseType === 'blob') {
            return res
        }
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) 
        return Promise.reject(error)
    }
)
export default slime

