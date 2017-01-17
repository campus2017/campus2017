import AppDispatcher from '../dispatcher/AppDispatcher';
import EventEmitter from 'events';

const Store = Object.assign({}, EventEmitter.prototype, {
	items: [{
		id: '0',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: false
		},
		flightInfos: [{
			go: {
				name: 'A航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: 'A航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}, {
		id: '1',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: false
		},
		flightInfos: [{
			go: {
				name: 'B航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: 'B航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}, {
		id: '2',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: false
		},
		flightInfos: [{
			go: {
				name: 'C航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: 'C航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}, {
		id: '3',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: true
		},
		flightInfos: [{
			go: {
				name: '达美航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: '达美航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}, {
		id: '4',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: false
		},
		flightInfos: [{
			go: {
				name: 'D航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: 'D航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}, {
		id: '5',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: false
		},
		flightInfos: [{
			go: {
				name: 'E航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: 'E航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}, {
		id: '6',
		tripInfo: {
			startTime: new Date().toISOString().match(/\d{4}-(\d{2}-\d{2})/)[1],
			endTime: '03-04',
			price: 3440,
			isLow: false
		},
		flightInfos: [{
			go: {
				name: 'F航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '首都机场 PEK T3',
				landAirport: '麦克卡兰国际机场 LAS',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			back: {
				name: 'F航空',
				width: 'DL128 330(宽)',
				height: 'DL4785 CRJ(小)',
				isCommon: true,
				launchTime: '01:20',
				landTime: '17:15',
				launchAirport: '麦克卡兰国际机场 LAS',
				landAirport: '首都机场 PEK T3',
				transferSite: '旧金山SFO',
				transferAirport: '洛杉矶机场'
			},
			recommend: [{
				site: '达美航空',
				price: 4091
			}, {
				site: '留学生票',
				price: 4085
			}],
			lowPrice: 2259,
			tax: 2784
		}]
	}],
	selectedId: '3',
	getItems() {
		return this.items;
	},
	getSelectedId() {
		return this.selectedId;
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
		case 'SELECT_DATE':
			Store.selectedId = action.id;
			Store.emitChange();
			break;
		default:
	}
});

export default Store;