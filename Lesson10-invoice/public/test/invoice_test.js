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
	
	testLineItemReturnsHtmlTableRow: function() {
		var lineItem = new LineItem("Pluto", 12301);
		this.assertEqual("<tr><td>Pluto</td><td>€ 123,01</td></tr>", lineItem.toHtml());
	},
	
	testLoadsDataFromAjax: function() {
		var data = [
		  { "description": "uno", "amountInCents": 123 }
		,  { "description": "due", "amountInCents": 345 }
		];
		var invoice = new Invoice();
		
		invoice.load(data);
		
		this.assertEqual(2, invoice.getSize());
		this.assertEqual("uno", invoice.getLineItem(0).description);
		this.assertEqual("due", invoice.getLineItem(1).description);
		this.assertEqual(123, invoice.getLineItem(0).amountInCents);
		this.assertEqual(345, invoice.getLineItem(1).amountInCents);
	},
	
	testProducesHtmlTable: function() {
		var invoice = new Invoice();
		this.assertEqual("<table>" + invoice.totalRowHtml() + "</table>", invoice.toHtml());
		
		invoice.add("pippo", 111);
		var expected = "<table>" + 
			new LineItem("pippo", 111).toHtml() + 
			invoice.totalRowHtml() + 
			"</table>";
		this.assertEqual(expected, invoice.toHtml());
	},
	
	testTotalRowHtml: function() {
		var invoice = new Invoice();
		this.assertEqual("<tr id='total'><td>Totale</td><td>€ 0,00</td><tr>", invoice.totalRowHtml());
	}

});
