<#ftl encoding='UTF-8'>

<#macro errorMessage error>
    <div class="error-message mx-auto">
        <div class="error-sign"></div>
        <h5 class="error-message-text">
            ${error.reason}
        </h5>
    </div>
</#macro>

<#macro successMessage success>
    <div class="success-message mx-auto">
        <div class="success-sign"></div>
        <h5 class="error-message-text">
            ${success.reason}
        </h5>
    </div>
</#macro>