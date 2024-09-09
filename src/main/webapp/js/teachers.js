document.getElementById("resetBtn").addEventListener("click", function(event) {
    // Prevent any default action of the button
    event.preventDefault();

    // Clear all input fields in the form
    document.getElementById("filterForm").reset();

    // Submit the form
    document.getElementById("filterForm").submit();
});