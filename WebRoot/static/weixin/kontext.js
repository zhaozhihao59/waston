window.kontext = function(container) {
	var changed = new kontext.Signal();
	var layers = Array.prototype.slice.call(container
			.querySelectorAll('.layer'));
	var capable = 'WebkitPerspective' in document.body.style
			|| 'MozPerspective' in document.body.style
			|| 'msPerspective' in document.body.style
			|| 'OPerspective' in document.body.style
			|| 'perspective' in document.body.style;
	if (capable) {
		container.classList.add('capable');
	}
	layers.forEach(function(el, i) {
		if (!el.querySelector('.dimmer')) {
			var dimmer = document.createElement('div');
			dimmer.className = 'dimmer';
			el.appendChild(dimmer);
		}
	});
	function show(target, direction) {
		layers = Array.prototype.slice.call(container
				.querySelectorAll('.layer'));
		container.classList.add('animate');
		direction = direction || (target > getIndex() ? 'right' : 'left');
		if (typeof target === 'string')
			target = parseInt(target);
		if (typeof target !== 'number')
			target = getIndex(target);
		target = Math.max(Math.min(target, layers.length), 0);
		if (layers[target] && !layers[target].classList.contains('show')) {
			layers.forEach(function(el, i) {
				el.classList.remove('left', 'right');
				el.classList.add(direction);
				if (el.classList.contains('show')) {
					el.classList.remove('show');
					el.classList.add('hide');
				} else {
					el.classList.remove('hide');
				}
			});
			layers[target].classList.add('show');
			changed.dispatch(layers[target], target);
		}
	}
	function prev() {
		var index = getIndex() - 1;
		show(index >= 0 ? index : layers.length + index, 'left');
	}
	function next() {
		show((getIndex() + 1) % layers.length, 'right');
	}
	function getIndex(of) {
		var index = 0;
		layers.forEach(function(layer, i) {
			if ((of && of == layer)
					|| (!of && layer.classList.contains('show'))) {
				index = i;
				return;
			}
		});
		return index;
	}
	function getTotal() {
		return layers.length;
	}
	return {
		show : show,
		prev : prev,
		next : next,
		getIndex : getIndex,
		getTotal : getTotal,
		changed : changed
	};
};
kontext.Signal = function() {
	this.listeners = [];
}
kontext.Signal.prototype.add = function(callback) {
	this.listeners.push(callback);
}
kontext.Signal.prototype.remove = function(callback) {
	var i = this.listeners.indexOf(callback);
	if (i >= 0)
		this.listeners.splice(i, 1);
}
kontext.Signal.prototype.dispatch = function() {
	var args = Array.prototype.slice.call(arguments);
	this.listeners.forEach(function(f, i) {
		f.apply(null, args);
	});
}