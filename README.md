## 本项目为使用RuoYi-Vue框架
  仓库内容为施工管理系统的前端代码,主要功能是实现施工方在线提交围蔽申请、交管方在线处理围蔽申请，并通过gis以实现施工项目、围蔽信息在地图上展示和操作。
  
## 开发

```bash


# 1、克隆项目
git clone http://10.28.1.70:10080/enclosed_design_sys/Web_Code.git

# 2、安装Node.js
官网https://nodejs.org/en/
安装node.js后，就已经自带npm，不需要另外再进行安装npm。
版本要求：node: >=8.9,
         npm: >= 3.0.0

# 3、安装依赖
npm install
npm install --registry=https://registry.npm.taobao.org # 可以通过如下命令解决 npm 下载速度慢的问题
(由于使用的rouyi框架，可由上面两个命令自动安装**所有**依赖）
所有依赖项如下：
    @riophae/vue-treeselect: 0.4.0,
    axios: 0.18.1,
    clipboard: 2.0.4,
    core-js: 3.6.5,
    echarts: 4.2.1,
    element-ui: 2.13.2,
    file-saver: 2.0.1,
    fuse.js: 3.4.4,
    js-beautify: 1.10.2,
    js-cookie: 2.2.0,
    jsencrypt: 3.0.0-rc.1,
    normalize.css: 7.0.0,
    nprogress: 0.2.0,
    path-to-regexp: 2.4.0,
    screenfull: 4.2.0,
    sortablejs: 1.8.4,
    vue: 2.6.10,              #vue由npm istall 命令安装
    vue-amap: ^0.5.10,
    vue-count-to: 1.0.13,
    vue-cropper: 0.4.9,
    vue-quill-editor: 3.0.6,
    vue-router: 3.0.2,
    vue-splitpane: 1.0.4,
    vuedraggable: 2.20.0,
    vuex: 3.1.0
    @vue/cli-plugin-babel: 4.4.4,
    @vue/cli-plugin-eslint: 4.4.4,
    @vue/cli-plugin-unit-jest: 4.4.4,
    @vue/cli-service: 4.4.4,
    @vue/test-utils: 1.0.0-beta.29,
    autoprefixer: 9.5.1,
    babel-eslint: 10.1.0,
    babel-jest: 23.6.0,
    babel-plugin-dynamic-import-node: 2.3.3,
    chalk: 2.4.2,
    chokidar: 2.1.5,
    connect: 3.6.6,
    eslint: 6.7.2,
    eslint-plugin-vue: 6.2.2,
    html-webpack-plugin: 3.2.0,
    husky: 1.3.1,
    lint-staged: 8.1.5,
    mockjs: 1.0.1-beta3,
    plop: 2.3.0,
    runjs: 4.3.2,
    node-sass: 4.14.1,
    sass-loader: 8.0.2,
    script-ext-html-webpack-plugin: 2.1.3,
    script-loader: 0.7.2,
    serve-static: 1.13.2,
    svg-sprite-loader: 4.1.3,
    svgo: 1.2.0,
    vue-template-compiler: 2.6.10

# 4、启动服务
npm run dev

浏览器访问 http://localhost:80


```


由于目前项目还未全部完成，所以成功运行前端代码需要启动后端，即	enclosed_design_sys /server_code

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod #编译后的文件将出现在dist文件夹中
```