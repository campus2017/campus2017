import AppDispatcher from '../dispatcher/AppDispatcher';
import EventEmitter from 'events';

const Stores = Object.assign({}, EventEmitter.prototype, {
	state: {
		maskShow: false,
		detailShow: false
	},
	getState() {
		return this.state;
	},
	getDetail(state) {
		this.state = state;
	},
	closeDetail(state) {
		this.state = state;
	},
	emitChange() {
		this.emit('change');
	},
	addChangeListener(callback) {
		this.on('change', callback);
	},
	removeChangeListener(callback) {
		this.removeListener('change', callback);
	}
});

AppDispatcher.register((action) => {
	switch (action.actionType) {
		case 'GET_DETAIL':
			Stores.getDetail(action.state);
			Stores.emitChange();
			break;
		case 'CLOSE_DETAIL':
			Stores.closeDetail(action.state);
			Stores.emitChange();
			break;
		default:
			break;
	}
});

export default Stores;