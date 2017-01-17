/**
 * @file component Mask
 */
import './index.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	shouldShow: PropTypes.bool.isRequired
};


class Mask extends React.Component {
	componentDidMount() {
		let viewWidth = document.body.clientWidth,
			viewHeight = document.body.clientHeight;
		this.refs.mask.style.width = viewWidth + 'px';
		this.refs.mask.style.height = viewHeight+ 'px';
	}
	render() {
		return (
			<div className= {  this.props.shouldShow ? "m-mask" : "hide" } ref="mask">
			</div>
		);
	}
}

Mask.propTypes = propTypes;

export default Mask;