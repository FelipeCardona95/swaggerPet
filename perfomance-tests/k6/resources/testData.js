// testData.js
export const validPetPayload = {
  id: 12345, // Valid ID
  name: "TestPet",
  category: { id: 1, name: "Dogs" },
  photoUrls: ["https://example.com/photo"],
  tags: [{ id: 1, name: "Friendly" }],
  status: "available",
};

export const invalidPetPayload = {
  id: "", // Invalid ID
  name: "", // Invalid Name
  category: { id: null, name: "" },
  photoUrls: ["invalid_url"],
  tags: [{ id: "", name: "" }],
  status: "unknown_status",
};
