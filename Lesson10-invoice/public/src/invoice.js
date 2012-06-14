function zeroPadding2(n) {
	var nAsString = "" + n;
	if (nAsString.length == 1) {
		return "0" + nAsString;
	}
	return nAsString;
}

function formatMoney(money) {
	return truncate(money / 100) +
		"," +
		zeroPadding2(money % 100);		
}

function truncate(n) {
  return Math[n > 0 ? "floor" : "ceil"](n);
}

function LineItem(description, amountInCents) {
	this.description = description;
	this.amountInCents = amountInCents;
}


LineItem.prototype.toHtml = function() {
	return "<tr><td>" + 
		this.description + 
		"</td><td>€ " + 
		formatMoney(this.amountInCents) +
		"</td></tr>";	
}

LineItem.prototype.toString = function() {
	return this.description + 
		": € " +
		formatMoney(this.amountInCents);
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

Invoice.prototype.load = function(data) {
	for (var i=0; i<data.length; i++) {
		var element = data[i];
		this.add(element.description, element.amountInCents);
	}
}

Invoice.prototype.toHtml = function() {
	var result = "<table>";
	for (var i=0; i<this.lineItems.length; i++) {
		result += this.lineItems[i].toHtml();
	}
	result += this.totalRowHtml();
	result += "</table>";
	return result;
}

Invoice.prototype.totalRowHtml = function() {
	var total = formatMoney(this.total);
	return "<tr id='total'><td>Totale</td><td>€ " + total + "</td><tr>";
}