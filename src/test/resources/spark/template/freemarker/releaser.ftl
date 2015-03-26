<h1>${message}</h1>

 <form action='/submit' method='POST'>
	<select id="repos" name="repos">
	    <#list repos as repo>        
	        <option value="${repo.svnURL}">${repo.svnURL}</option>
	    </#list>
	</select>
	<input type="submit" value="Submit">
</form> 