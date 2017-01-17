'use strict';
let gulp = require('gulp'),
	sass = require('gulp-sass'),
	uglify = require('gulp-uglify'),
	minify = require('gulp-minify-css'),
	browserSync = require('browser-sync').create();

//Static server
gulp.task('browser-sync', () => {
	browserSync.init({
		server: {
			baseDir: './src'
		}
	});
});

//Build sass files
gulp.task('compressCSS', function() {
	gulp.src('src/styles/**/*.scss')
		.pipe(sass({
			outputStyle: 'compressed'
		}))
		.on('error', sass.logError)
		.pipe(gulp.dest('src/styles'));
});

//Reload dev files
gulp.task('reloadIndex', () => {
	gulp.src('src/*.html')
		.pipe(browserSync.stream());
});
gulp.task('reloadHTML', () => {
	gulp.src('src/html/**/*.html')
		.pipe(browserSync.stream());
});
gulp.task('reloadCSS', () => {
	gulp.src('src/styles/**/*.scss')
		.pipe(browserSync.stream());
});
gulp.task('reloadJS', () => {
	gulp.src('src/scripts/**/*.js')
		.pipe(browserSync.stream());
});

//Watch files for changes & recompile
gulp.task('watch', () => {
	gulp.watch(['src/*.html'], ['reloadIndex']);
	gulp.watch(['src/styles/**/*.scss'], ['compressCSS', 'reloadCSS']);
	gulp.watch(['src/html/**/*.html'], ['reloadHTML']);
	gulp.watch(['src/scripts/**/*.js'], ['reloadJS']);
});

//Default task
gulp.task('default', ['compressCSS', 'browser-sync', 'watch']);


//Publish html to prd
gulp.task('publishHTML', () => {
	gulp.src('src/**/*.html')
		.pipe(gulp.dest('prd'));
});

//Publish css to prd
gulp.task('publishCSS', () => {
	gulp.src('src/styles/**/*.css')
		.pipe(minify())
		.pipe(gulp.dest('prd/styles'));
});

//Publish imgs to prd
gulp.task('publicIMG', () => {
	gulp.src(['src/**/*.png', 'src/**/*.jpg', 'src/**/*.gif'])
		.pipe(gulp.dest('prd'));
});

//Public js to prd
gulp.task('publishJS', () => {
	gulp.src('src/scripts/**/*.js')
		.pipe(uglify())
		.pipe(gulp.dest('prd/scripts'));
});

gulp.task('prd', ['publishHTML', 'publishCSS', 'publicIMG', 'publishJS']);