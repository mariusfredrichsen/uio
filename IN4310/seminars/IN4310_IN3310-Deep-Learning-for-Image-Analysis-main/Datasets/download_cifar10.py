import torchvision
import os

def download_cifar10():
    """
    Downloads the CIFAR-10 dataset to 'Datasets/CIFAR10'.
    """
    base_dir = os.path.dirname(os.path.abspath(__file__))
    
    print(f"🚀 Downloading CIFAR-10 to: {base_dir}")
    os.makedirs(base_dir, exist_ok=True)

    print("Downloading Training set...")
    torchvision.datasets.CIFAR10(
        root=base_dir, 
        train=True, 
        download=True
    )
    
    print("Downloading Test set...")
    torchvision.datasets.CIFAR10(
        root=base_dir, 
        train=False, 
        download=True
    )

    print(f"✅ Done! Data is located at: {os.path.join(base_dir, 'cifar-10-batches-py')}")

if __name__ == "__main__":
    download_cifar10()