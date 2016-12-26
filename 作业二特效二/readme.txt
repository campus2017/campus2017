作者：刘心乔
不足：编写的时候因没有考虑兼容IE，测试在IE8-IE5的时候,页面乱掉，
background-image，background-size,等都不支持，导致整个效果无法
呈现。而且获取currentStyle时获取到的msTransform属性有些浏览器下
是用matrix显示，IE8及以下没有做这个统一，还是获得translateX(),
导致IE8及以下版本出现bug.