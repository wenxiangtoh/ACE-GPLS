export interface ContactNumber {
  countryCode: number;
  number: string;
}

export interface CreateFeedbackRequest {
  name: string,
  email: string,
  contactNumber: ContactNumber;
  text: string;
  agencyUuid: string;
}

export interface LookupListApiItem extends Record<string, string> {
  code: string;
  description: string;
}

export interface AgencyDropdownList {
  agencies: LookupListApiItem[];
}

export interface FeedbackInfoRequest {
  email: string;
  contactNumber: ContactNumber;
}

export interface FeedbackInfo {
  name: string;
  email: string;
  contactNumber: ContactNumber;
  agency: string;
  status: string;
  text: string;
}

export interface FeedbackInfoResponse {
  feedbackInfo: FeedbackInfo[];
}

