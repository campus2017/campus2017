/**
 * Created by Administrator on 2017/1/26.
 */
var mainApp = angular.module('myApp', []);
(function () {
    mainApp.controller('myCtrl', ['$scope', 'myService', mainCtrl]);
    function mainCtrl($scope, myService) {
        $scope.init = function () {
            $scope.form = {};
            $scope.form.radio_method = 0;//初始化单选按钮
            $scope.form.content = '';
            $scope.form.uploadName = '';
            $scope.form.warnings = {};
            $scope.form.warnings.title = '';
            $scope.form.warnings.animate = function (title) {
                var warningEle = angular.element('#warnings');
                if (warningEle.css('opacity') == 0) {
                    $scope.form.warnings.title = title;
                    warningEle.animate({opacity: 1}, '500').delay('1500').animate({opacity: 0}, '500');
                }
            }
            $scope.form.url = ['content', 'upload'];
            $scope.words = {};
            $scope.words.sort = [{
                key: '',
                value: ''
            }, {
                key: '',
                value: ''
            }, {
                key: '',
                value: ''
            }];
            $scope.words.statistics = {
                us: '',
                cn: '',
                number: '',
                sign: ''
            }
        }();

        $scope.clearContent = function () {
            $scope.form.content = '';
        };
        $scope.statistics = function () {
            if ($scope.form.radio_method == 0) {
                if ($scope.form.uploadName.length != 0) {
                    $scope.form.upload = myService.getFormData(angular.element('input[type=file]'));
                    myService.getStatisticsData($scope.form.upload, $scope.form.url[1]).then(function (data) {
                        $scope.words.sort = data.sortWord;
                        $scope.words.statistics = data.statisticsWord;
                    }, function (status) {
                        if (status == 500) {
                            $scope.form.warnings.animate('无法统计，请选择其它文件上传！');
                        } else if (status = 404) {
                            $scope.form.warnings.animate('请求超时，无法连接服务器！');
                        }
                    });
                } else {
                    $scope.form.warnings.animate('请先上传文件！');
                }
            } else {
                if ($scope.form.content.length != 0) {
                    myService.getStatisticsData($scope.form.content, $scope.form.url[0]).then(function (data) {
                        $scope.words.sort = data.sortWord;
                        $scope.words.statistics = data.statisticsWord;
                    }, function (err) {
                        if (status == 500) {
                            $scope.form.warnings.animate('无法统计，请重新输入文字！');
                        } else if (status = 404) {
                            $scope.form.warnings.animate('请求超时，无法连接服务器！');
                        }
                    });
                } else {
                    $scope.form.warnings.animate('请先输入一段文字！');
                }
            }
        }
        $scope.fileNameChanged = function () {
            var filePath = angular.element('input[type=file]').val();
            $scope.$apply(function () {
                $scope.form.uploadName = myService.getUploadName(filePath);
            });
        }
    };
})();
