import { TextDataResponse, TextDataListResponse, CreateTextDataRequest } from '../types/TextData';

export const createTextData = async (textContent: string): Promise<TextDataResponse> => {
  const requestBody: CreateTextDataRequest = {
    textData: {
      textContent: textContent
    }
  };

  const response = await fetch('/textdata', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(requestBody)
  });

  if (!response.ok) {
    const error = await response.text();
    throw new Error(`Error saving text data: ${error}`);
  }

  return response.json();
};

export const getAllTextData = async (): Promise<TextDataListResponse> => {
  const response = await fetch('/textdata');
  
  if (!response.ok) {
    throw new Error('Error loading text data');
  }

  return response.json();
};
