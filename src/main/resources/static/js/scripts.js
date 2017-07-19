$(function() {
	$('#all-tasks').on('click', '.delete-task', function(e) {
		let task = $(this).closest('.task');
		let taskId = task.data('id');
		
		$.ajax({
		    url: `/todo-list/${taskId}`,
		    type: 'DELETE',
		    success: function(result) {
		    	task.remove();
		    }
		});
	});
	
	$('#all-tasks').on('click', '.update-task', function(e) {
		let task = $(this).closest('.task');
		let taskText = task.find('.title-text');
		let taskUpdateButton = task.find('.update-task');
		
		let text = taskText.text();
		task.prepend(`<input type="text" value="${text}" class="update-text form-control pull-left input-sm">`);
		taskUpdateButton.val('Save');
		taskUpdateButton.removeClass('update-task');
		taskUpdateButton.addClass('save-updated-task');
		
		taskText.remove();
	});
	
	$('#all-tasks').on('click', '.save-updated-task', function(e) {
		let task = $(this).closest('.task');
		let taskInput = task.find('.update-text');
		let taskUpdateButton = task.find('.save-updated-task');
		let taskId = task.data('id');
		
		let text = taskInput.val();
		
		$.ajax({
			contentType: 'application/json',
		    url: `/todo-list/${taskId}`,
		    type: 'PUT',
		    dataType: 'json',
		    data: JSON.stringify({ text: text }),
		    success: function(taskResponse) {
				task.prepend(`<span class="title-text">${taskResponse.title}</span>`);
				taskUpdateButton.val('Update');
				taskUpdateButton.removeClass('save-updated-task');
				taskUpdateButton.addClass('update-task');
				
				taskInput.remove();
		    },
			error: function() {
				alert('Request failed');
		    }
		});
		
	});
	
	$('#add-task').click(function(e) {
		let taskText = $('#new-task-text').val();
		
		$.ajax({
			contentType: 'application/json',
		    url: `/todo-list/`,
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify({ text: taskText }),
		    success: function(task) {
		    	$('#new-task-list-item').before(`
		    		<li data-id="${task.id}" class="task list-group-item">
						<span class="title-text">${task.title}</span>
						<div class="pull-right">
							<input class="btn btn-primary update-task" type="button" value="Update"/>
							<input class="btn btn-danger delete-task" type="button" value="Delete"/>
						</div>
					</li>
		    	`);
		    },
			error: function() {
				alert('Request failed');
		    }
		});
	});
	
	
}); 
