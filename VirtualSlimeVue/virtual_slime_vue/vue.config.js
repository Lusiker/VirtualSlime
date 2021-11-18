export const devServer = {
    port: 8080,
    proxy: {
        '/api': {
            target: 'http://localhost:8964',
            changeOrigin: true,
            pathRewrite: {
                '/api': ''
            }
        }
    }
};
