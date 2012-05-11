<div id="pay_station">
	<p>Minuti acquistati: ${minutes!0}</p>
	<form method='post'>
		<input type="hidden" name="state" value="${state!""}" />
		<input type="submit" name="coin05" value="5 &cent;" />
		<input type="submit" name="coin10" value="10 &cent;" />
		<input type="submit" name="coin25" value="25 &cent;" />
		<input type="submit" name="cancel" value="Annulla" />
		<input type="submit" name="buy" value="Acquista" />
	</form>
</div>
