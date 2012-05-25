function truncate(n) {
  return Math[n > 0 ? "floor" : "ceil"](n);
}

function LineItem(description, amountInCents) {
	this.description = description;
	this.amountInCents = amountInCents;
}

LineItem.prototype.zeroPadding2 = function(n) {
	var nAsString = "" + n;
	if (nAsString.length == 1) {
		return "0" + nAsString;
	}
	return nAsString;
}


LineItem.prototype.toString = function() {
	return this.description + 
		": € " +
		truncate(this.amountInCents / 100) +
		"," +
		this.zeroPadding2(this.amountInCents % 100)
		;
}



function Invoice() {
	this.total = 0;
	this.size = 0;
	this.lineItems = [];
}

Invoice.prototype.getTotal = function() {
	return this.total;
}

Invoice.prototype.add = function(description, amount) {
	this.total += parseInt(amount);
	this.lineItems[this.size++] = new LineItem(description, amount);
}

Invoice.prototype.getSize = function() {
	return this.size;
}

Invoice.prototype.getLineItem = function(index) {
	return this.lineItems[index];
}