$.ajaxSetup({
  url: '/line-items',
  success: function(data) {
		var invoice = new Invoice();
		invoice.load(data);
    $("#invoice").html(invoice.toHtml());
  },
  error: function(xhr, textStatus, errorThrown) {
    $("#invoice").text("Error: " + textStatus + "; errorThrown " + errorThrown);
  },
});

function loadInvoice() {
  $.ajax({});
}

function addLineItemWithAjax(ex) {
  $.ajax({
		type: 'POST',
		data: $("form").serialize(),
  });
	return false;
}

$(document).ready(function() {
  loadInvoice();
	$("form").submit(addLineItemWithAjax);
});
