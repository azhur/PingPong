<html>
<head>
	<title></title>
</head>
<body>
<table>
<#--<#include "_header.ftl">-->
	<tr>
		<td width="20"></td>
		<td style="font-family: Arial; color: #666; padding: 20px 30px;">
			<h3 style="font-size: 16px;">Dear Admin,</h3>

			<p style="font-size: 14px">
				New player has been registered and it requires you action (player will not be able to use system without your approval)
			<table>
				<tr>
					<td>Player Name</td>
					<td>${form.name}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${form.email}</td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>${form.gender}</td>
				</tr>
				<tr>
					<td>Birth</td>
					<td>${form.birth}</td>
				</tr>
			</table>
			</p>
		</td>
		<td width="20"></td>
	</tr>
<#--<#include "_footer.ftl">-->
</table>

</body>

</html>