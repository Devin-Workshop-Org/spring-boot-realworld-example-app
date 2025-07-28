export interface TextData {
  id: string;
  textContent: string;
}

export interface TextDataResponse {
  textData: TextData;
}

export interface TextDataListResponse {
  textDataList: TextData[];
}

export interface CreateTextDataRequest {
  textData: {
    textContent: string;
  };
}
