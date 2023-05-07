module.exports = {
    outputDir: 'dist',
    publicPath: '/',
    indexPath: '../../templates/index.html',
    chainWebpack: config => {
      config
        .entry('app')
        .clear()
        .add('./main.js');
    },
  };
  
/*   module.exports = {
    publicPath: process.env.NODE_ENV === "production" ? "/static/" : "/",
    outputDir: "static/dist",
  }; */
  