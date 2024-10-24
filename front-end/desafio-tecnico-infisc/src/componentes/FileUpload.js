import React, { useState } from 'react';
import axios from 'axios';

const FileUpload = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploadStatus, setUploadStatus] = useState('');

  // Função para lidar com a seleção de arquivos
  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  // Função para enviar o arquivo
  const handleFileUpload = async (event) => {
    event.preventDefault();

    if (!selectedFile) {
      setUploadStatus('Por favor, selecione um arquivo antes de enviar.');
      return;
    }

    // Criar o FormData
    const formData = new FormData();
    formData.append('arquivo', selectedFile); // Certifique-se de usar o nome correto no backend

    try {
      // Substitua pela URL da sua API (agora com http://)
      const response = await axios.post('http://localhost:8080/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      
      // Lidar com a resposta da API
      setUploadStatus(`Upload bem-sucedido: ${response.data.message}`);
    } catch (error) {
      setUploadStatus(`Erro no upload: ${error.message}`);
    }
  };

  return (
    <div>
      <h1>Upload de Arquivo</h1>
      <form onSubmit={handleFileUpload}>
        <input type="file" onChange={handleFileChange} />
        <button type="submit">Enviar</button>
      </form>
      {uploadStatus && <p>{uploadStatus}</p>}
    </div>
  );
};

export default FileUpload;
