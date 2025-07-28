import React, { useState, useEffect } from 'react';
import { getAllTextData } from '../services/api';
import { TextData } from '../types/TextData';

interface TextDataListProps {
  refreshTrigger: number;
}

const TextDataList: React.FC<TextDataListProps> = ({ refreshTrigger }) => {
  const [textDataList, setTextDataList] = useState<TextData[]>([]);
  const [loading, setLoading] = useState(true);

  const loadTextData = async () => {
    try {
      const result = await getAllTextData();
      setTextDataList(result.textDataList || []);
    } catch (error) {
      console.error('Error loading text data:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadTextData();
  }, [refreshTrigger]);

  const escapeHtml = (text: string): string => {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="text-data-list">
      <h2>Saved Text Data</h2>
      <div>
        {textDataList.length === 0 ? (
          <p>No text data saved yet.</p>
        ) : (
          textDataList.map((item) => (
            <div key={item.id} className="text-data-item">
              <div 
                className="text-data-content"
                dangerouslySetInnerHTML={{ __html: escapeHtml(item.textContent) }}
              />
              <div className="text-data-meta">
                ID: {item.id}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default TextDataList;
