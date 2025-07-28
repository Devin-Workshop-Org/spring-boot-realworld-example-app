import React, { useState } from 'react';
import { createTextData } from '../services/api';

interface TextDataFormProps {
  onSuccess: () => void;
}

const TextDataForm: React.FC<TextDataFormProps> = ({ onSuccess }) => {
  const [textContent, setTextContent] = useState('');
  const [message, setMessage] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    const trimmedContent = textContent.trim();
    if (!trimmedContent) {
      setMessage('<div class="error">Please enter some text</div>');
      return;
    }

    setIsSubmitting(true);
    setMessage('');

    try {
      await createTextData(trimmedContent);
      setMessage('<div class="success">Text data saved successfully!</div>');
      setTextContent('');
      onSuccess();
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : 'Network error occurred';
      setMessage(`<div class="error">${errorMessage}</div>`);
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleRefresh = () => {
    onSuccess();
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="textContent">Enter your text:</label>
          <textarea
            id="textContent"
            name="textContent"
            placeholder="Type your text here..."
            value={textContent}
            onChange={(e) => setTextContent(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={isSubmitting}>
          Save Text Data
        </button>
        <button type="button" onClick={handleRefresh}>
          Refresh List
        </button>
      </form>
      
      {message && (
        <div dangerouslySetInnerHTML={{ __html: message }} />
      )}
    </>
  );
};

export default TextDataForm;
