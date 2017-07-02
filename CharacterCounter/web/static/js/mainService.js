/**
 * Created by Administrator on 2017/1/26.
 */
(function () {
    mainApp.service('myService', ['$http', '$q', mainService]);
    function mainService($http, $q) {
        this.getStatisticsData = getStatisticsData;
        this.getUploadName = getUploadName;
        this.getFormData = getFormData;

        function getStatisticsData(data, url) {
            var promise = $q.defer();
            var config = {
                method: 'post',
                url: '/CharacterCounter/' + url,
                data: data,
            };
            if (url == 'upload') {
                config = angular.extend(config, {
                    headers: {'Content-Type': undefined, 'charset': 'UTF-8'},
                    transformRequest: angular.identity
                });
            }
            $http(config).success(function (data) {
                promise.resolve(data);
            }).error(function (err, status, headers, config) {
                promise.reject(status);
            });
            return promise.promise;
        };

        function getUploadName(filePath) {
            if (filePath.length == 0) {
                return '';
            } else {
                var index = filePath.lastIndexOf('\\');
                var fileName = filePath.slice(index + 1, filePath.length);
                return fileName;
            }
        };

        function getFormData(element) {
            var fd = new FormData();
            fd.append('upload', element[0].files[0]);
            return fd;
        }
    };
})();
