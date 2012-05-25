var invoiceTest = TestCase.create({
    name: 'Invoice test',

    testTheTruth: function() {
        this.assertEquals(0, 0);        
    },

	testTotalIsZeroInitially: function() {
		var invoice = new Invoice();
		this.assertEquals(0, invoice.getTotal());
	},
	
	testComputesTotal: function() {
		var invoice = new Invoice();
		invoice.add("Voce1", 100);
		invoice.add("Voce2", 200);
		this.assertEquals(300, invoice.getTotal());
	},
	
	testConvertsStringToNumber: function() {
		var invoice = new Invoice();
		invoice.add("Voce1", "100");
		invoice.add("Voce2", "200");
		this.assertEquals(300, invoice.getTotal());
	},
	
	testParsesComma: function() {
		var invoice = new Invoice();
		invoice.add("Voce1", "1,23");
		this.assertEquals(123, invoice.getTotal());
	},
	
	testReturnsFormattedLineItems: function() {
		var invoice = new Invoice();
		invoice.add("Prima voce", 1);
		invoice.add("Seconda voce", 567);
		this.assertEquals(2, invoice.getSize());
		this.assertEquals("Prima voce", invoice.getLineItem(0).description);
		this.assertEquals(1, invoice.getLineItem(0).amountInCents);
	},
	
	testLineItemReturnsFormattedString: function() {
		var lineItem = new LineItem("Pippo", 12301);
		this.assertEqual("Pippo: € 123,01", lineItem.toString());
	},

});
