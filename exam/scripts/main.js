window.onload = function () {
    // 单选框点击
    var labelEle = document.getElementsByTagName('label');
    [].slice.call(labelEle).map(function (item) {
        item.addEventListener('click', function () {
            var onRadioEle = document.getElementsByClassName('icon-radio-on')[0];
            if (onRadioEle) {
                onRadioEle.classList.remove('icon-radio-on');
                onRadioEle.classList.add('icon-radio')
            };
            this.getElementsByClassName('iconfont')[0].classList.remove('icon-radio');
            this.getElementsByClassName('iconfont')[0].classList.add('icon-radio-on');
            document.getElementsByClassName('submit-btn')[0].removeAttribute('disabled');
        })
    });

    // iscroll区域滚动
    var myScroll = new IScroll('#reason-list-wrapper', { mouseWheel: true });
    document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

    // 弹出层
    var submitBtn = document.getElementsByClassName('submit-btn')[0];
    submitBtn.addEventListener('click', function (e) {
        e.preventDefault();
        var overlayEle = document.createElement("div"),
            layerEle = document.createElement("div");

        // 遮罩层
        overlayEle.className = "overlay";
        overlayEle.style.height = document.documentElement.scrollHeight + "px";
        overlayEle.style.width = document.documentElement.scrollWidth + "px";
        document.body.appendChild(overlayEle);

        // 模态窗口
        layerEle.className = "layer";
        layerEle.id = 'layer';
        var layerCloseBtn = document.createElement("div");
        layerCloseBtn.className = "layer-close";
        var layerEleHeader = document.createElement('div');
        layerEleHeader.className = 'layer-header';
        layerEleHeader.innerHTML = '提示';
        layerEleHeader.appendChild(layerCloseBtn);
        layerEle.appendChild(layerEleHeader);
        var layerEleContent = document.createElement('div');
        layerEleContent.className = 'layer-content';
        layerEleContent.appendChild(document.createTextNode('病退的退款申请审核为14天，建议您耐心等待，如果您仍有问题，请点击发送问题给客服，我们将会有客服与您联系。'))
        layerEle.appendChild(layerEleContent);
        var layerEleFooter = document.createElement('div');
        layerEleFooter.className = 'layer-footer';
        layerEleFooter.innerHTML = '<a href="">发送问题给客服</a><a href="">知道了</a>'
        layerEle.appendChild(layerEleFooter);
        document.body.appendChild(layerEle);

        layerEle.style.left = (document.documentElement.clientWidth - layerEle.offsetWidth) / 2 + 'px';

        var vScroll = (document.documentElement.clientHeight - layerEle.offsetHeight) / 2 + 'px';

        $('#layer').animate({ top: vScroll }, 500, 'ease-in-out');

        layerCloseBtn.addEventListener("click", function () {
            $('#layer').animate({
                top: document.documentElement.scrollHeight + 100 + "px"
            }, 500, 'ease-in-out', function () {
                document.body.removeChild(overlayEle);
                document.body.removeChild(layerEle);
            })
        })
    })
}
