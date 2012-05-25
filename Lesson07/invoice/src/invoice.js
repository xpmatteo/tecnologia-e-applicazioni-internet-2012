
function Invoice() {
	
}

Invoice.prototype.add = function(description, amount) {
	// ...
}


// test
var invoice = new Invoice();
invoice.add("Latte", 1.50);
invoice.add("Giornale", 1.50);
invoice.add("Pane", 7.00);

console.log("Totale: " + invoice.getTotal());
console.log("Totale con IVA: " + invoice.getTotalWithVat());
// Mi aspetto di leggere:
// Totale: 10.0
// Totale con IVA: 12.1

