<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<h2>Órarend - for-each, value-of</h2>
				<table border="4">
					<tr bgcolor="blue">
						<th>ID</th>
						<th>Típus</th>
						<th>Tárgynév</th>
						<th>Nap</th>
			            <th>Tól</th>
				        <th>Ig</th>
						<th>Helyszín</th>
						<th>Oktató</th>
						<th>Szak</th>
					</tr>

					<!-- for-each feldolgozasi utasitás -->

					<xsl:for-each select="DJ7PNE_orarend/ora">
						<tr>
							<td>
								<!-- value of instruction -->
								<xsl:value-of select="@id" />
							</td>
							<td>
								<xsl:value-of select="@tipus" />
							</td>
							<td>
								<xsl:value-of select="targy" />
							</td>
							<td>
								<xsl:value-of select="//nap" />
							</td>
							<td>
								<xsl:value-of select="//tol" />
							</td>
							<td>
								<xsl:value-of select="//ig" />
							</td>
							<td>
								<xsl:value-of select="helyszin" />
							</td>
							<td>
								<xsl:value-of select="oktato" />
							</td>
							<td>
								<xsl:value-of select="szak" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>


</xsl:stylesheet>