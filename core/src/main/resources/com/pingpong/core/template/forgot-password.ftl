<html>
<head>
	<title></title>
</head>
<body>
<table>
<#include "_header.ftl">
	<tr>
		<td width="20"></td>
		<td style="font-family: Arial; color: #666; padding: 20px 30px;">
			<p>
				You (or someone else) has requested forgot of password.
				<br/>
				Passwords are not kept in plain text and can't send you them.
				Use provided below link to set a new password.
			</p>

			<p>
				Use <a href="${resetPasswordUrl}">${resetPasswordUrl}</a> to reset it.
			</p>
		</td>
		<td width="20"></td>
	</tr>
<#include "_footer.ftl">
</table>

</body>

</html>