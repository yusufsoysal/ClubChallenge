zconst path = require('path');
const webpack = require('webpack');


module.exports = {
    entry: ['./src/main/js/app.js'],
    output: {
        path : path.resolve(__dirname, 'src/main/resources/static/js'),
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                loader: 'babel-loader',
                test: /\.js$/,
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
                loader: "file-loader"
            },
            {
                test: /\.(woff|woff2)$/,
                loader: "url-loader",
                options: {
                    limit: 50000,
                    mimetype: "application/font-woff",
                    name: "[name].[ext]", // Output below ./fonts
                    publicPath: "js/"
                },
            },
            {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                loader: "url-loader",
                options: {
                    limit: 10000,
                    mimetype: "application/octet-stream"
                }
            },
            {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                loader: "url-loader",
                options: {
                    limit: 10000,
                    mimetype: "image/svg+xml"
                }
            }
        ]
    },
    resolve: {
        alias: {
            jquery: "jquery/src/jquery"
        }
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ],
    devServer: {
        port: 3000,
        contentBase: './build',
        inline: true
    }
};
