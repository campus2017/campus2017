(function () {

    var main = document.getElementById('main');
    var body = document.body;

    var mask = document.createElement('div'),
        layer = document.createElement('div'),
        close = document.createElement('div');

    var img=new Image();
    img.src='images/layer.png';
    img.width='100%';

    mask.appendChild(layer);
    layer.appendChild(img);
    layer.appendChild(close);

    mask.className='m-mask';
    layer.className='layer';
    close.className='close-btn';

    main.onclick = function () {
        console.log('main click');
        body.appendChild(mask);
    };

    close.onclick = function () {
        console.log('close button click');
        body.removeChild(mask);
    };

})();