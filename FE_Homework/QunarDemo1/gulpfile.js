var gulp = require('gulp');
var sass = require('gulp-sass');
var minifyCSS = require('gulp-minify-css');
var rename = require('gulp-rename');
var browserSync = require('browser-sync').create();

// Static server
gulp.task('browser-sync', function() {
    browserSync.init({
        server: {
            baseDir: './'
        }
    });
});

// Build and Reload css files
gulp.task('compressCSS', function() {
    gulp.src('src/styles/**/*.scss')
        .pipe(sass({
            outputStyle: 'compressed'
        }))
        .on('error', sass.logError)
        .pipe(minifyCSS())
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('prd/styles'))
        .pipe(browserSync.stream());
});

// Reload js files
gulp.task('compressJS', function() {
    gulp.src('src/scripts/**/*.js')
        .pipe(gulp.dest('prd/scripts'))
        .pipe(browserSync.stream());
});

// Reload HTML file
gulp.task('reloadHTML', function() {
    gulp.src('./index.html')
        .pipe(browserSync.stream());
});

// Watch files for changes & recompile
gulp.task('watch', function() {
    gulp.watch(['src/styles/**/*.scss'], ['compressCSS']);
    gulp.watch(['src/scripts/**/*.js'], ['compressJS']);
    gulp.watch(['./index.html'], ['reloadHTML']);
});

// Default task, running just `gulp` will move font, compress js and scss, start server, watch files.
gulp.task('default', ['compressCSS', 'compressJS', 'browser-sync', 'watch']);