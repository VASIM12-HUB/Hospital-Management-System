document.addEventListener('DOMContentLoaded', function() {
    // Initialize Bootstrap tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Handle form submissions with fetch API
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const formData = new FormData(this);
            const action = this.getAttribute('action');
            const method = this.getAttribute('method') || 'POST';

            try {
                const response = await fetch(action, {
                    method: method,
                    body: formData,
                    headers: {
                        'Accept': 'application/json'
                    }
                });

                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Error submitting form');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('An error occurred');
            }
        });
    });

    // Handle delete buttons
    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            if (confirm('Are you sure you want to delete this?')) {
                const id = this.getAttribute('data-id');
                const entity = this.getAttribute('data-entity');
                
                fetch(`/${entity}/delete/${id}`, {
                    method: 'GET'
                }).then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert('Error deleting record');
                    }
                });
            }
        });
    });

    // Initialize datepickers
    $('input[type="datetime-local"]').flatpickr({
        enableTime: true,
        dateFormat: "Y-m-d H:i",
        minDate: "today"
    });
});